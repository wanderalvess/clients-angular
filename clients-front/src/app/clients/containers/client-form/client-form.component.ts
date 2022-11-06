import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

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
      Validators.maxLength(100)]],
    document: ['',
      [Validators.required,
      Validators.minLength(11),
      Validators.maxLength(14)]],
    address: ['',
      [Validators.required,
      Validators.minLength(5),
      Validators.maxLength(100)]],
    latitude: ['',
      [Validators.required,
      Validators.minLength(5),
      Validators.maxLength(100)]],
    longitude: ['',
      [Validators.required,
      Validators.minLength(5),
      Validators.maxLength(100)]]
  });

  constructor(private formBuilder: NonNullableFormBuilder,
    private service: ClientsService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute) {
    //this.form
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

  lat = '-49.257813';
  lng = '-49.257813';
  map = null;

  public recentralizarMapa(client: Client) {

    this.lat = client.latitude;
    this.lng = client.longitude;
  }

  onSubmit() {
    this.service.save(this.form.value)
    .subscribe(result => this.onSucess(), error => this.onError());
  }

  onCancel() {
    this.location.back();
  }

  private onSucess() {
    this.snackBar.open('Cliente salvo com sucesso!', '', { duration: 4000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar novo cliente', '', { duration: 4000 });
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
}
