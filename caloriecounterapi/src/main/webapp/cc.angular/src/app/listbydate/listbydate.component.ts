import {Component, OnInit} from '@angular/core';
import {ItemService} from "../item/item.service";
import {Item} from "../item/item";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-listbydate',
  templateUrl: './listbydate.component.html',
  styleUrls: ['./listbydate.component.css']
})
export class ListbydateComponent implements OnInit{

  title = "Find Items By Date";

  dates! : Date[];

  constructor(private itemService: ItemService, private datePipe: DatePipe) {
  }
  ngOnInit(): void {
    this.getDates();
  }

  getDates(){
    this.itemService.getDates().subscribe(
      dates => this.dates = dates
    )
  }
  getDayOfWeek(date: Date): string {
    return <string>this.datePipe.transform(date, 'EEEE'); // 'EEEE' format will give the full name of the day of the week
  }

  // getItemsOfDate(date : Date){
  //   this.itemService.getItemsOfDate(date).subscribe(
  //     items => this.items = items
  //   )
  // }
}
