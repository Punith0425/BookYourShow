import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Userpayment } from './userpayment';

describe('Userpayment', () => {
  let component: Userpayment;
  let fixture: ComponentFixture<Userpayment>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Userpayment]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Userpayment);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
