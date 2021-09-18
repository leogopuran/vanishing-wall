import { TestBed } from '@angular/core/testing';

import { CreateNewPostService } from './create-new-post.service';

describe('CreateNewPostService', () => {
  let service: CreateNewPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateNewPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
