package com.ReactiveProg.routers;

import com.ReactiveProg.handlers.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> bookRoutes(BookHandler bookHandler) {
        return route()
                .nest(path("/books"),  // Prefix all routes under /books
                        builder -> builder
                                .POST(bookHandler::create)              // Handles POST /books
                                .GET(bookHandler::getAll)               // Handles GET /books
                                .GET("/{bid}", bookHandler::get)            // Handles GET /books/{bid}
                                .PUT("/{bookId}", bookHandler::update)      // Handles PUT /books/{bookId}
                                .DELETE("/{bookId}", bookHandler::delete)   // Handles DELETE /books/{bookId}
                )
                .build();
    }

}