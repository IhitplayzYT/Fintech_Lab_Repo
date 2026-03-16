import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router'
import { ApiService } from '../../services/api'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'
import { ENTITY_CONFIG } from '../../models/entity-config'

@Component({

selector:'app-form',

standalone:true,

imports:[CommonModule,FormsModule],

templateUrl:'./form.html'

})
export class FormComponent implements OnInit{

entity!:string
fields:string[]=[]
data:any={}
id?:number

constructor(
private route:ActivatedRoute,
private router:Router,
private api:ApiService
){}

ngOnInit(){

this.route.paramMap.subscribe(params=>{

this.entity = params.get('entity')!

/* THIS LINE UPDATES FORM FIELDS */

this.fields = ENTITY_CONFIG[this.entity] || []

/* RESET DATA WHEN ENTITY CHANGES */

this.data = {}

const id = params.get('id')

if(id){

this.id = Number(id)

this.api.get(this.entity,this.id)
.subscribe(d => this.data = d)

}

})

}

save(){

if(this.id){

this.api.update(this.entity,this.id,this.data)
.subscribe(()=>this.router.navigate(['/table',this.entity]))

}else{

this.api.create(this.entity,this.data)
.subscribe(()=>this.router.navigate(['/table',this.entity]))

}

}

}