import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Userregister } from './userregister';

describe('Userregister', () => {
  let component: Userregister;
  let fixture: ComponentFixture<Userregister>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Userregister]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Userregister);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
