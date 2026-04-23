import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Page } from '../models/page'

@Injectable({
providedIn:'root'
})
export class ApiService{

base="http://localhost:8080/api"

constructor(private http:HttpClient){}

getPage(entity:string,page:number,size:number):Observable<Page<any>>{

return this.http.get<Page<any>>(
`${this.base}/${entity}/page?page=${page}&size=${size}`
)

}

get(entity:string,id:number){

return this.http.get(`${this.base}/${entity}/${id}`)

}

create(entity:string,data:any){

return this.http.post(`${this.base}/${entity}`,data)

}

update(entity:string,id:number,data:any){

return this.http.put(`${this.base}/${entity}/${id}`,data)

}

delete(entity:string,id:number){

return this.http.delete(`${this.base}/${entity}/${id}`)

}

}