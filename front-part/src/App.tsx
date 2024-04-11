import { CssBaseline } from "@mui/material"
import { RouterProvider } from "react-router"
import { router } from "./router/router"
import { RatingProvider } from "./context/rating"

function App() {
  return (
    <>
      <CssBaseline />
      <RatingProvider>
        <RouterProvider router={router} />
      </RatingProvider>
    </>
  )
}

export default App
