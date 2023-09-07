import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ItemComponent } from './item/item.component';
import { ItemListComponent } from './item.list/item.list.component';
import { ListbydateComponent }  from './listbydate/listbydate.component';

const routes: Routes = [
  { path: 'add', component: ItemComponent }, // Specific route first
  { path: 'list', component: ItemListComponent },
  { path: 'edit/:id', component: ItemComponent }, // Specific route second
  { path: 'listbydate', component: ListbydateComponent},
  { path: 'listofitemsbydate/:date', component:ItemListComponent},
  { path: '', redirectTo: 'add', pathMatch: 'full' } // Generic route last
];


@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports:[RouterModule]
})
export class AppRoutingModule { }
