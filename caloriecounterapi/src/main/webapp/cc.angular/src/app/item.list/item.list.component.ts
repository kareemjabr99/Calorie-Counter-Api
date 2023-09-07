import {Component, OnInit} from '@angular/core';
import {Item} from "../item/item";
import {ItemService} from "../item/item.service";
import {ItemComponent} from "../item/item.component";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-item.list',
  templateUrl: './item.list.component.html',
  styleUrls: ['./item.list.component.css']
})
export class ItemListComponent implements OnInit{
  title = 'Item List';

  noItems = "No Items";

  loadingMessage = "Loading Data, Please Wait..."

  totalCalories: number = 0;

  items!: Item[];
  constructor(
    private itemService: ItemService,
    private router: Router,
    private route: ActivatedRoute ) {
  }
  ngOnInit(): void {
    this.getItems()
    // this.totCalories();
  }
  getItems(){
    const dateParam: string | null = this.route.snapshot.paramMap.get("date");
    if(dateParam) {
      // const date: Date = new Date(dateParam);
      console.log(dateParam);
      this.itemService.getItemsOfDate(dateParam).subscribe(
        items => {
          this.items = items;
          for (let i = 0; i < items.length; i++) {
            if(items[i] !== null) {
              this.totalCalories += items[i].calories;
            }
          }
        },
        (error) => {
          console.log(this.items)
          console.error(error);
          alert("Failed to get items by date item. Error details logged to console.");
        }
      )
    }
    else {
      this.itemService.getItems().subscribe(
        items => {
          this.items = items
          for (let i = 0; i < items.length; i++) {
            if(items[i] !== null) {
              this.totalCalories += items[i].calories;
            }
          }
        },
        (error) => {
          console.error(error);
          alert("Failed to get all items. Error details logged to console.");
        }
      )
    }
  }

  // totCalories(){
  //
  // }

  deleteItem(item: Item) {
    console.log("Deleting item with id: " + item.item_id);
    if (confirm("Are you sure you want to delete " + item.name + " ?")) {
      this.itemService.deleteItem(item.item_id).subscribe(
        () => {
          console.log("Item Deleted Successfully");
          alert("Item Deleted Successfully");
          this.getItems();
        },
        (error) => {
          console.error(error);
          alert("Failed to delete item. Error details logged to console.");
        }
      );
    }
  }
}
