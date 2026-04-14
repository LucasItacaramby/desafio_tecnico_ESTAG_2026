import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Cidade} from '@domain/cidade';
import {Observable} from 'rxjs';

@Injectable()
export class ProjetoService {

  constructor(private http: HttpClient) {}

    //------------------------------------------------
    /** Recupera a lista de cidades */
    //------------------------------------------------
    pesquisarCidades(): Observable<Cidade[]> {
      return this.http.get<Cidade[]>('http://localhost:8080/placeti/cidades');
    }

    //------------------------------------------------
    /** Exclui a cidade informada */
    //------------------------------------------------
    excluir(cidade: Cidade): Observable<any> {
      return this.http.delete(`http://localhost:8080/placeti/cidades/${cidade.id}`);
    }

    //------------------------------------------------
    /** Salva a cidade informada */
    //------------------------------------------------
    salvar(cidade: Cidade): Observable<any> {
      if(cidade.id) {
        return this.http.put('http://localhost:8080/placeti/cidades', cidade);
      }

      return this.http.post('http://localhost:8080/placeti/cidades', cidade);
    }
}
