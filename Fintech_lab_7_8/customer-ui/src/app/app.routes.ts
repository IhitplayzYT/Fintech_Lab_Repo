import { Routes } from '@angular/router'

import { DashboardComponent } from './pages/dashboard/dashboard'
import { CrudComponent } from './pages/crud/crud'
import { FormComponent } from './pages/form/form'

export const routes:Routes=[

{path:'',component:DashboardComponent},

{path:'table/:entity',component:CrudComponent},

{path:'form/:entity',component:FormComponent},
{path:'form/:entity/:id',component:FormComponent}

]