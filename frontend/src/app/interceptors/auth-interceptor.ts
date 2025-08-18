import {Injectable} from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import {AuthService} from '../services/auth.service';
import {Observable} from 'rxjs';
import {Globals} from '../global/globals';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  /**
    * List of URLs to skip for adding authrorization header.
  */
  private readonly exactUrls: String[];

  /**
    * List of regex patterns to match URLS that should skip interception
  */
  private readonly regexPatterns: RegExp[];

  /**
    * Constructs the AuthInterceptor with dependencies and initializes URL patterns.
    *
    * @param authService AuthService for retrieving the JWT token.
    * @param globals Globals service providing the backend URI.
  */
  constructor(private authService: AuthService, private globals: Globals) {
    this.exactUrls = [
    `${this.globals.backendUri}/authentication`
    ]

    this.regexPatterns = [

    ]
  }

  /**
    * Intercepts outgoing HTTP requests to conditionally add Authorization header.
    *
    * @param req The original HTTP request.
    * @param next The next handler in the HTTP pipeline.
    * @returns The potentially modified HTTP request observable.
  */
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    // Do not intercept authentication requests
    if (this.shouldSkipInterception(req.url)) {
      return next.handle(req);
    }

    const authReq = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + this.authService.getToken())
    });

    return next.handle(authReq);
  }

  /**
    * Checks whether a request URL should be excluded from interception.
    *
    * @param url The URL of the request.
    * @returns True if the URL should be skipped, false otherwise.
  */
  private shouldSkipInterception(url: string): boolean {
    if (this.exactUrls.includes(url)) {
      return true
    }

    return this.regexPatterns.some((pattern) => pattern.test(url));

  }
}
