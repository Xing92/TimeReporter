import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee } from '../models/employee';


@Injectable()
export class DataService {

  constructor(private http: HttpClient) { }

  url = 'http://localhost:8080/api/employee';

  postEmployee (firstName: string, lastName: string, pesel: string) {
    return this.http.post<Employee>(this.url + '?firstName=' + firstName + '&lastName=' + lastName + '&pesel=' + pesel, null);
  }


}
