import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Role } from '../model/role'
import { Url } from '../constants/url';
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  roleUrl: string = Url.BASE_URL+'/role';

  constructor(private http: HttpClient) { }

  getRoles() {
    debugger 
    return this.http.get<Role[]>(this.roleUrl);
  }

  getRoleById(id: number) {
    return this.http.get<Role>(this.roleUrl + '/' + id);
  }

  createRole(role: Role) {
    return this.http.post(this.roleUrl, role);
  }

  updateRole(role: Role) {
    return this.http.put(this.roleUrl, role);
  }

  deleteRole(id: number) {
    return this.http.delete(this.roleUrl + '/' + id);
  }
}
