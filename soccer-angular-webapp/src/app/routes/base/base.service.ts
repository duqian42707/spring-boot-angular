import { Injectable } from '@angular/core';
import { _HttpClient } from '@delon/theme';

@Injectable()
export class BaseService {

  constructor(private http: _HttpClient) { }

}
