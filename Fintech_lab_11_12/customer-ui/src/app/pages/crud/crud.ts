import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, RouterModule } from '@angular/router'
import { ApiService } from '../../services/api'
import { CommonModule } from '@angular/common'
import { ENTITY_CONFIG } from '../../models/entity-config'

@Component({
  selector: 'app-crud',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './crud.html',
  styleUrl: './crud.css'
})
export class CrudComponent implements OnInit {
  entity!: string
  page: any
  fields: string[] = []
  pageIndex = 0
  size = 10

  constructor(
    private route: ActivatedRoute,
    private api: ApiService,
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const newEntity = params.get('entity')!

      if (newEntity !== this.entity) {
        this.pageIndex = 0
      }

      this.entity = newEntity
      this.fields = ENTITY_CONFIG[this.entity] || []
      this.load()
    })
  }

  load() {
    this.api.getPage(this.entity, this.pageIndex, this.size)
      .subscribe({
        next: res => {
          this.page = res
        },
        error: err => {
          console.error('Failed to load', err)
        }
      })
  }

  reloadPage(): void {
    this.load()  
  }

  next() {
    this.pageIndex++
    this.load()
  }

  prev() {
    if (this.pageIndex > 0) {
      this.pageIndex--
      this.load()
    }
  }

  delete(id: number) {
    this.api.delete(this.entity, id)
      .subscribe(() => this.load())
  }
getdate(s: string): string {
  const idx = s.indexOf('T');
  return idx === -1 ? s : s.slice(0, idx);
}
isISODate(val: any): boolean {
  return typeof val === 'string' && /^\d{4}-\d{2}-\d{2}T/.test(val);
}
getOrdinal(n: number): string {
  const mod100 = n % 100;
  if (mod100 >= 11 && mod100 <= 13) return 'th';

  switch (n % 10) {
    case 1: return 'st';
    case 2: return 'nd';
    case 3: return 'rd';
    default: return 'th';
  }
}
fmtdate(s: string): string {
  if (!s) return s;

  const [y, m, d] = s.split('-');
  const months = [
    'January','February','March','April','May','June',
    'July','August','September','October','November','December'
  ];

  const day = parseInt(d, 10);
  const suffix = this.getOrdinal(day);

  return `${day}${suffix},${months[parseInt(m,10)-1]} ${y}`;
}
capital(str: string): string {
  if (!str) {
    return str; 
  }
  return str.charAt(0).toUpperCase() + str.slice(1);
}

}