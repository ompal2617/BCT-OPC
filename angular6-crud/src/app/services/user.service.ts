import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user'
import { Url } from '../constants/url';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userUrl: string = Url.BASE_URL+'/user';

  constructor(private http: HttpClient) { }

  getUsers() {
    return this.http.get<User[]>(this.userUrl);
  }

  getUserById(id: number) {
    return this.http.get<User>(this.userUrl + '/' + id);
  }

  createUser(user: User) {
    return this.http.post(this.userUrl, user);
  }

  updateUser(user: User) {
    return this.http.put(this.userUrl, user);
  }

  deleteUser(id: number) {
    return this.http.delete(this.userUrl + '/' + id);
  }
}
