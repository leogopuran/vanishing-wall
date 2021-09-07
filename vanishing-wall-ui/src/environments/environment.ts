// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  socket_api_url: "http://192.168.1.224:8080/ws",
  default_topic: "/topic/posts/default",
  max_post_count: 5,
  posts_on_demand_url: "http://192.168.1.224:8080/api/getPostsOndemand",
  defaultImage: "../assets/images/placeholder-img.png"
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
