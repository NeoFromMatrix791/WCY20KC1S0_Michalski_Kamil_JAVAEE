import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HashDTO } from '../models/hash-dto.model';
import { DateUtil } from '../utils/date.util';

@Injectable({
    providedIn: 'root'
})
export class HashService {

    constructor(private readonly http: HttpClient) {
    }

    public generateHash(input: string): Observable<HashDTO> {
        const host: string = environment.host;
        const contextRoot: string = environment.contextRoot;

        const params: HttpParams = new HttpParams()
            .set('input', input);
        return this.http.get<HashDTO>(`${host}/${contextRoot}hasher-api/hash`, { params });
    }

    public getAllHashes(): Observable<HashDTO[]> {
        const host: string = environment.host;
        const contextRoot: string = environment.contextRoot;
     
        return this.http.get<HashDTO[]>(`${host}/${contextRoot}data-api/hash`)
            .pipe(
                map((result: HashDTO[]) => result.map((dto: HashDTO) => {
                    dto.created = DateUtil.format(dto.created);
                    return dto;
                })
                ));
    }

    public saveHash(dto: HashDTO): Observable<unknown> {
        const host: string = environment.host;
        const contextRoot: string = environment.contextRoot;
  
        return this.http.post(`${host}/${contextRoot}data-api/hash`, dto);
    }

    public deleteHash(dto: HashDTO): Observable<unknown> {
        const host: string = environment.host;
        const contextRoot: string = environment.contextRoot;
   
        const params: HttpParams = new HttpParams()
            .set('hash128', dto.hash128)
            .set('hash256', dto.hash256)
            .set('hash512', dto.hash512);

        return this.http.delete(`${host}/${contextRoot}data-api/hash`, { params });
    }
}
