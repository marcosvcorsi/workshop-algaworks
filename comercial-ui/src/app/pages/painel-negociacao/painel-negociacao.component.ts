import { Component, OnInit } from '@angular/core';
import { OportunidadeService } from 'src/app/services/oportunidade.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-painel-negociacao',
  templateUrl: './painel-negociacao.component.html',
  styleUrls: ['./painel-negociacao.component.css']
})
export class PainelNegociacaoComponent implements OnInit {

  oportunidade = {};
  oportunidades = [];

  constructor(private opService: OportunidadeService,
              private msgService: MessageService) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.opService.findAll().subscribe(result => {
      this.oportunidades = <any> result;
    });
  }

  add(){
    this.opService.save(this.oportunidade).subscribe(() => {
      this.oportunidade = {}
      this.getAll();

      this.msgService.add({
        severity: 'success',
        summary: 'Oportunidade adicionada com sucesso',
        detail: 'Ok',
      });
    }, err => {
      this.msgService.add({
        severity: 'error',
        summary: 'Erro ao salvar oportunidade',
        detail: 'Error',
      });
    });
  }

}
