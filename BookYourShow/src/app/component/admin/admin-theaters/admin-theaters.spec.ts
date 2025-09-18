import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTheaters } from './admin-theaters';

describe('AdminTheaters', () => {
  let component: AdminTheaters;
  let fixture: ComponentFixture<AdminTheaters>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminTheaters]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminTheaters);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
