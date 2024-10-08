import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ICustomer } from 'app/entities/customer/customer.model';
import { CustomerService } from 'app/entities/customer/service/customer.service';
import { IWishList } from '../wish-list.model';
import { WishListService } from '../service/wish-list.service';
import { WishListFormGroup, WishListFormService } from './wish-list-form.service';

@Component({
  standalone: true,
  selector: 'jhi-wish-list-update',
  templateUrl: './wish-list-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class WishListUpdateComponent implements OnInit {
  isSaving = false;
  wishList: IWishList | null = null;

  customersSharedCollection: ICustomer[] = [];

  protected wishListService = inject(WishListService);
  protected wishListFormService = inject(WishListFormService);
  protected customerService = inject(CustomerService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: WishListFormGroup = this.wishListFormService.createWishListFormGroup();

  compareCustomer = (o1: ICustomer | null, o2: ICustomer | null): boolean => this.customerService.compareCustomer(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ wishList }) => {
      this.wishList = wishList;
      if (wishList) {
        this.updateForm(wishList);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const wishList = this.wishListFormService.getWishList(this.editForm);
    if (wishList.id !== null) {
      this.subscribeToSaveResponse(this.wishListService.update(wishList));
    } else {
      this.subscribeToSaveResponse(this.wishListService.create(wishList));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWishList>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(wishList: IWishList): void {
    this.wishList = wishList;
    this.wishListFormService.resetForm(this.editForm, wishList);

    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing<ICustomer>(
      this.customersSharedCollection,
      wishList.customer,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.customerService
      .query()
      .pipe(map((res: HttpResponse<ICustomer[]>) => res.body ?? []))
      .pipe(
        map((customers: ICustomer[]) =>
          this.customerService.addCustomerToCollectionIfMissing<ICustomer>(customers, this.wishList?.customer),
        ),
      )
      .subscribe((customers: ICustomer[]) => (this.customersSharedCollection = customers));
  }
}
