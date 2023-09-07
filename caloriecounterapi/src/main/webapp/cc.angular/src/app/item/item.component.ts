import { Component, OnInit } from '@angular/core';
import { Item } from './item';
import { ItemService} from "./item.service";
import { Router, ActivatedRoute } from "@angular/router";


@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  item!: Item;

  constructor(
    private itemService: ItemService,
    private router: Router,
    private route: ActivatedRoute) { }
  ngOnInit() {
    this.getItem();
  }

  getItem(): void{
    const id = +this.route.snapshot.paramMap.get("id")!;
    if( id!=0 ){
      this.itemService.getItem(id).subscribe(
        (item) => {
          // item.date = new Date(item.date)
          console.log('Fetched item:', item); // Log the item object
          this.item = item;
        }
      );
    }
    else {
      this.item = new Item(0, "", 0, new Date());
    }
  }

  saveItem(): void{
    console.log(this.item);
    this.itemService.saveItem(this.item).subscribe(
      item => {
        this.item = item
        console.log(this.item.name + "saved succesfuly");
        this.router.navigate(["/list"]);
      }
    )
  }

}
