import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { ItemComponent } from './item/item.component';
import { ItemListComponent } from './item.list/item.list.component';

import { ItemService } from "./item/item.service";
import { ListbydateComponent } from './listbydate/listbydate.component';
import {DatePipe} from "@angular/common";


@NgModule({
  declarations: [
    AppComponent,
    ItemComponent,
    ItemListComponent,
    ListbydateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ItemService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
