import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Userbooking } from './userbooking';

describe('Userbooking', () => {
  let component: Userbooking;
  let fixture: ComponentFixture<Userbooking>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Userbooking]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Userbooking);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
