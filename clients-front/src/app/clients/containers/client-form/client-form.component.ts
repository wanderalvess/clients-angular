import { MapComponent } from '../../../../app/shared/components/map/map.component';
import { Location } from '@angular/common';
import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { NonNullableFormBuilder, Validators, FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

import { Client } from '../../model/client';
import { ClientsService } from '../../services/clients.service';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.scss']
})

export class ClientFormComponent implements OnInit, AfterViewInit {

  form = this.formBuilder.group({
    _id: [''],
    name: ['',
      [Validators.required,
      Validators.minLength(4),
      Validators.maxLength(100),
      Validators.nullValidator]],
    document: ['',
      [Validators.required,
      Validators.minLength(11),
      Validators.maxLength(18),
      Validators.nullValidator]],
    address: ['',
      [Validators.required,
      Validators.minLength(5),
      Validators.maxLength(100),
      Validators.nullValidator]],
    latitude: ['',
      [Validators.required,
      Validators.minLength(5),
      Validators.maxLength(100),
      Validators.nullValidator]],
    longitude: ['',
      [Validators.required,
      Validators.minLength(5),
      Validators.maxLength(100),
      Validators.nullValidator]]
  });

  constructor(private formBuilder: NonNullableFormBuilder,
    private service: ClientsService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute) {
    //this.form
   }

  @ViewChild(MapComponent) map?: MapComponent;

  onDocumentInput() {
    const documentField = this.form.get('document');
    if (documentField) {
      const formattedValue = this.formatDocument(documentField.value);
      documentField.setValue(formattedValue);
    }
  }

  formatDocument(value: string): string {
    value = value.replace(/\D/g, '');

    if (value.length === 11) {
      return value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
    } else {
      return value.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5');
    }
  }

  ngOnInit(): void {
    const client: Client = this.route.snapshot.data['client']
    if (client.latitude && client.longitude) {
      this.map?.addMarker(Number(client.latitude), Number(client.longitude));
    }
    this.form.setValue({
      _id: client._id,
      name: client.name,
      document: client.document,
      address: client.address,
      latitude: client.latitude,
      longitude: client.longitude
    });
  }
  ngAfterViewInit(): void {
    const client = this.form.value;
    if (client.latitude && client.longitude) {
      this.map?.addMarker(Number(client.latitude), Number(client.longitude));
    }
  }

  onSubmit() {
    this.service.save(this.form.value)
    .subscribe(result => this.onSucess(), error => this.onError(error));
  }

  onCancel() {
    this.location.back();
  }

  private onSucess() {
    this.snackBar.open('O Cadastro do Cliente foi salvo com sucesso!', '', { duration: 4000 });
    this.onCancel();
  }

  private onError(error: HttpErrorResponse) {
    let errorMessage = 'Erro ao salvar o cadastro do cliente, verifique se preencheu todos os campos';
    if (error && error.error && error.error.message) {
      errorMessage = error.error.message;
    }
    this.snackBar.open(errorMessage, '', { duration: 4000 });
  }

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if (field?.hasError('minlength')) {
      const requiredLength: number = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Tamanho mínimo precisa ser de ${requiredLength} caracteres.`;
    }

    if (field?.hasError('maxlength')) {
      const requiredLength: number = field.errors ? field.errors['maxlength']['requiredLength'] : 200;
      return `Tamanho máximo excedido de ${requiredLength} caracteres.`;
    }

    return 'Campo Inválido';
  }

  findCoordinates(): void {
    if(!this.form.value.latitude?.length || !this.form.value.longitude?.length) {
      this.snackBar.open('É necessário preencher os campos de latitude e longitude!', '', { duration: 4000 });
    }
    const latitude = this.form.value.latitude;
    const longitude = this.form.value.longitude;
    const coordinates = `${latitude},${longitude}`;
    this.map?.findCoordinate(coordinates);
  }
}
