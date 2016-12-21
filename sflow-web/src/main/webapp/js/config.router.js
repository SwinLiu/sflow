'use strict';

/**
 * Config for the router
 */
angular.module('app')
  .run(
    [          '$rootScope', '$state', '$stateParams', '$http', '$localStorage', 
      function ($rootScope,   $state,   $stateParams,   $http,   $localStorage,   AUTH_EVENTS,   AuthService
    		  ) {
    	  
    	  $http.defaults.headers.common['X-API-Token'] = $localStorage.token;
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;
          
          $rootScope.$on('$stateChangeStart', function (event, next) {
        	  
//    	    var authorizedRoles = next.data.authorizedRoles;
//    	    if (!AuthService.isAuthorized(authorizedRoles)) {
//    	      event.preventDefault();
//    	      if (AuthService.isAuthenticated()) {
//    	        // user is not allowed
//    	        $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
//    	      } else {
//    	        // user is not logged in
//    	        $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
//    	      }
//    	    }
        	  
    	  });
          
      }
    ]
  )
  .config(
    [          '$stateProvider', '$urlRouterProvider',
      function ($stateProvider,   $urlRouterProvider) {
          
    	  $urlRouterProvider
    	  	  .otherwise('/auth/loading');
    	
          $stateProvider
	          .state('auth',{
	              abstract: true,
	              url:'/auth',
	              template: '<div ui-view class="fade-in"></div>',
	              resolve: {
	                  deps: ['$ocLazyLoad',
	                    function( $ocLazyLoad ){
	                      return $ocLazyLoad.load('js/controllers/auth.js');
	                  }]
	              }
	          })
	          .state('auth.loading',{
	              url:'/loading',
	              templateUrl:'tpl/auth/loading.html',
	          })
	          .state('auth.login',{
	              url:'/login',
	              templateUrl:'tpl/auth/signin.html',
	          })
              .state('app', {
                  abstract: true,
                  url: '/app',
                  templateUrl: 'tpl/app.html'
              })
              .state('app.dashboard', {
                  url: '/dashboard',
                  templateUrl: 'tpl/app_dashboard.html',
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                        return $ocLazyLoad.load(['js/controllers/chart.js']);
                    }]
                  }
              })

              // fullCalendar
              .state('app.calendar', {
                  url: '/calendar',
                  templateUrl: 'tpl/app_calendar.html',
                  // use resolve to load other dependences
                  resolve: {
                      deps: ['$ocLazyLoad', 'uiLoad',
                        function( $ocLazyLoad, uiLoad ){
                          return uiLoad.load(
                            ['vendor/jquery/fullcalendar/fullcalendar.css',
                              'vendor/jquery/fullcalendar/theme.css',
                              'vendor/jquery/jquery-ui-1.10.3.custom.min.js',
                              'vendor/libs/moment.min.js',
                              'vendor/jquery/fullcalendar/fullcalendar.min.js',
                              'js/app/calendar/calendar.js']
                          ).then(
                            function(){
                              return $ocLazyLoad.load('ui.calendar');
                            }
                          )
                      }]
                  }
              })

              .state('layout', {
                  abstract: true,
                  url: '/layout',
                  templateUrl: 'tpl/layout.html'
              })
              .state('layout.fullwidth', {
                  url: '/fullwidth',
                  views: {
                      '': {
                          templateUrl: 'tpl/layout_fullwidth.html'
                      },
                      'footer': {
                          templateUrl: 'tpl/layout_footer_fullwidth.html'
                      }
                  },
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/controllers/vectormap.js'] );
                      }]
                  }
              })
              .state('layout.mobile', {
                  url: '/mobile',
                  views: {
                      '': {
                          templateUrl: 'tpl/layout_mobile.html'
                      },
                      'footer': {
                          templateUrl: 'tpl/layout_footer_mobile.html'
                      }
                  }
              })
              .state('layout.app', {
                  url: '/app',
                  views: {
                      '': {
                          templateUrl: 'tpl/layout_app.html'
                      },
                      'footer': {
                          templateUrl: 'tpl/layout_footer_fullwidth.html'
                      }
                  },
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/controllers/tab.js'] );
                      }]
                  }
              })
      }
    ]
  );