import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { switchMap, tap } from 'rxjs';
import { HashDTO } from './models/hash-dto.model';
import { HashService } from './services/hash.service';
import { NotificationService } from './services/notification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  displayedColumns: string[] = ['hash128', 'hash256', 'hash512', 'created', 'actions'];
  dataSource: MatTableDataSource<HashDTO> = new MatTableDataSource();

  form: FormGroup;
  hashFC: FormControl;
  saveFC: FormControl;

  result: HashDTO;

  constructor(private readonly hashService: HashService,
    private readonly formBuilder: FormBuilder,
    private readonly notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.hashFC = new FormControl('', Validators.compose([Validators.required]));
    this.saveFC = new FormControl();
    this.saveFC.disable();

    this.form = this.formBuilder.group({
      hash: this.hashFC,
      save: this.saveFC
    });

    this.refreshData();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  generateHash(): void {
    const value: string = this.hashFC.value;
    const withSaveMode: boolean = this.saveFC.value;

    if (withSaveMode) {
      this.hashService.generateHash(value)
        .pipe(
          tap((dto: HashDTO) => this.result = dto),
          switchMap((dto: HashDTO) => this.hashService.saveHash(dto))
        )
        .subscribe(() => {
          this.notificationService.success('Result has been stored in Database');
          this.form.reset();
          this.refreshData();
        });
    } else {
      this.hashService.generateHash(value)
        .subscribe((dto: HashDTO) => this.result = dto);
    }
  }

  removeHash(dto: HashDTO): void {
    this.hashService.deleteHash(dto)
      .subscribe(() => {
        this.notificationService.success('Element has been removed');
        this.refreshData();
      })
  }

  private refreshData(): void {
    this.hashService.getAllHashes()
      .subscribe((response: HashDTO[]) => this.dataSource.data = response);
  }
}
