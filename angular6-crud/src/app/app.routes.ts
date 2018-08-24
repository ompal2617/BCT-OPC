import { RouterModule,Routes } from '@angular/router';  
import { AddUserComponent } from './components/user/add-user/add-user.component';
import { ShowUserComponent } from './components/user/show-user/show-user.component';
import { AddRoleComponent } from './components/role/add-role/add-role.component';
import { ShowRoleComponent } from './components/role/show-role/show-role.component';

export const appRoutes: Routes = [ 
    { path: '', component: ShowUserComponent },
    { path: 'add-user', component: AddUserComponent } ,
    { path: 'show-users', component: ShowUserComponent } ,
    { path: 'add-role', component: AddRoleComponent },
    { path: 'show-roles', component: ShowRoleComponent } 
];

export const routing = RouterModule.forRoot(appRoutes);