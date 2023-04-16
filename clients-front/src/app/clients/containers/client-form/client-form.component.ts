import { MapComponent } from '../../components/map/map.component';
import { Location } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
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

export class ClientFormComponent implements OnInit {


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
    this.form.setValue({
      _id: client._id,
      name: client.name,
      document: client.document,
      address: client.address,
      latitude: client.latitude,
      longitude: client.longitude
    });
  }

  onSubmit() {
    this.service.save(this.form.value)
    .subscribe(result => this.onSucess(), error => this.onError(error));
  }

  onCancel() {
    this.location.back();
  }

  private onSucess() {
    this.snackBar.open('Cliente salvo com sucesso!', '', { duration: 4000 });
    this.onCancel();
  }

  private onError(error: HttpErrorResponse) {
    let errorMessage = 'Erro ao salvar novo cliente';
    if (error && error.error && error.error.message) { // Verificando se a mensagem de erro está presente no objeto de erro
      errorMessage = error.error.message; // Obtendo a mensagem de erro do objeto de erro
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
    const latitude = this.form.get('latitude');
    const longitude = this.form.get('longitude')
    const coordenadas = `${latitude},${longitude}`;
    this.map?.findCoordinate(coordenadas);
  }
}
