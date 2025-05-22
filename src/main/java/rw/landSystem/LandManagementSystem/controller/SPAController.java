package rw.landSystem.LandManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Single Page Application Controller
 *
 * This controller handles all non-API routes and forwards them to the React frontend.
 * It ensures that client-side routing works correctly by forwarding all requests that
 * don't have a file extension to the root of the application, where the React router
 * will handle them.
 */
@Controller
public class SPAController {

    /**
     * Captures all routes without file extensions and forwards them to the root
     * This allows React Router to handle client-side routing
     *
     * @return forward to the root path
     */
    @RequestMapping(value = "/{path:[^\\.]*}/**")
    public String redirect() {
        return "forward:/";
    }
}