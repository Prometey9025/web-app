import { createBrowserRouter } from "react-router-dom";
import { PAboutAs, PCompetitionsCreate, PCompetitionsList, PCompetitionsView, PCoursesCreate, PCoursesList, PCoursesView, PNotFound, PRegistration, PSignIn } from "../pages";
import { BLayoutApp } from "../layouts";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <BLayoutApp />,
    children: [
      {
        index: true,
        element: <PAboutAs />,
      },
      {
        path: "courses",
        element: <PCoursesList />,
      },
      {
        path: "courses/:id",
        element: <PCoursesView />
      },
      {
        path: "courses/create",
        element: <PCoursesCreate />,
      },
      {
        path: "сompetitions",
        element: <PCompetitionsList />,
      },
      {
        path: "сompetitions/:id",
        element: <PCompetitionsView />,
      },
      {
        path: "сompetitions/create",
        element: <PCompetitionsCreate />,
      },
      {
        path: "registration",
        element: <PRegistration />,
      },
      {
        path: "signin",
        element: <PSignIn />,
      },
    ]
  },
  {
    path: '*',
    element: <PNotFound />
  }
]);