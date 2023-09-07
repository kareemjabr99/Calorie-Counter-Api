export class Item {
    item_id: number;
    name: string;
    calories: number;
    dateOfItem: Date;

    constructor(item_id: number, name: string, calories: number, dateOfItem: Date){
        this.item_id = item_id;
        this.name = name;
        this.calories = calories;
        this.dateOfItem = new Date(dateOfItem);
    }

}
