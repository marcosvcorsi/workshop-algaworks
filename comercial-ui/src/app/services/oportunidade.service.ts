import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment'
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class OportunidadeService {

  URI = environment.API_URI + '/oportunidades'

  constructor(private http: HttpClient) { }

  findAll() {
    return this.http.get(this.URI);
  }

  save(oportunidade){
    return this.http.post(this.URI, oportunidade);
  }
}
