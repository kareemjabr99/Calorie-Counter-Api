import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListbydateComponent } from './listbydate.component';

describe('ListbydateComponent', () => {
  let component: ListbydateComponent;
  let fixture: ComponentFixture<ListbydateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListbydateComponent]
    });
    fixture = TestBed.createComponent(ListbydateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
