import { AppMaterialModule } from './app-material/app-material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorDialogComponent } from './components/error-dialog/error-dialog.component';
import { MapComponent } from './components/map/map.component';



@NgModule({
  declarations: [
    ErrorDialogComponent,
    MapComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule
  ],
  exports: [
    ErrorDialogComponent,
    MapComponent
  ]
})
export class SharedModule { }
