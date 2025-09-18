import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Usermybooking } from './usermybooking';

describe('Usermybooking', () => {
  let component: Usermybooking;
  let fixture: ComponentFixture<Usermybooking>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Usermybooking]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Usermybooking);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
