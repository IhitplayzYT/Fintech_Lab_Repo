import { Component,OnInit } from '@angular/core'
import { ActivatedRoute,RouterModule } from '@angular/router'
import { ApiService } from '../../services/api'
import { CommonModule } from '@angular/common'
import { ENTITY_CONFIG } from '../../models/entity-config'
@Component({

selector:'app-crud',

standalone:true,

imports:[CommonModule,RouterModule],

templateUrl:'./crud.html',

styleUrl:'./crud.css'

})
export class CrudComponent implements OnInit{

entity!:string
page:any

fields:string[]=[]
pageIndex=0
size=10

constructor(
private route:ActivatedRoute,
private api:ApiService,
){}

ngOnInit(){

this.route.paramMap.subscribe(params=>{

this.entity = params.get('entity')!

this.fields = ENTITY_CONFIG[this.entity] || []

this.pageIndex = 0

this.load()

})

}

load(){

this.page = null

this.api.getPage(this.entity,this.pageIndex,this.size)
.subscribe(res=>{

this.page = res

})

}

reloadPage(): void {
  this.load();
}


next(){

this.pageIndex++
this.load()

}

prev(){

if(this.pageIndex>0){

this.pageIndex--
this.load()

}

}

delete(id:number){

this.api.delete(this.entity,id)
.subscribe(()=>this.load())

}

}