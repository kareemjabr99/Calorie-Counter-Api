import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Item } from "./item";

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private getItemServiceURL = "http://localhost:8080/cc"
  constructor( private http: HttpClient) { }

  getItems() : Observable<Item[]>{
    return this.http.get<Item[]>(this.getItemServiceURL + "/getItems")
  }
  getItem(id: number) : Observable<Item>{
    return this.http.get<Item>(this.getItemServiceURL + "/getItem/" + id);
  }
  deleteItem(id: number) : Observable<any>{
    return this.http.delete<any>(this.getItemServiceURL + "/deleteItem/" + id);
  }
  saveItem(item: Item) : Observable<Item>{
    return this.http.post<Item>(this.getItemServiceURL + "/item", item);
  }
  updateItem(id: number, item: Item) : Observable<Item>{
    return this.http.put<Item>(this.getItemServiceURL + "/updateItem/" + id, item);
  }
  getDates() : Observable<Date[]>{
    return this.http.get<Date[]>(this.getItemServiceURL + "/listOfDates");
  }
  getItemsOfDate(date : string) : Observable<Item[]>{
    return this.http.get<Item[]>(this.getItemServiceURL + "/getitemsofdate/" + date);
  }
}
