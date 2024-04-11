import { Alert, AlertTitle, Button, Container, TextField, Typography } from "@mui/material"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

export const PSignIn = () => {
  const navigate = useNavigate()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [checkAuthError, setCheckAuthError] = useState(false)

  const onSubmit = () => {
    const emailLS = localStorage.getItem('email')
    const passwordLS = localStorage.getItem('password')
    if(passwordLS !== password || emailLS !== email) {
      setCheckAuthError(true)
    } else {
      localStorage.setItem("auth", 'true');
      setCheckAuthError(false)
      navigate('/сompetitions')
    }
  }

  return (
    <Container
      component='main'
      maxWidth='xs'
      sx={{
        height: 'calc(100% - 400px)',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'stretch',
        justifyContent: 'center',
        gap: '20px'
      }}
    >
      <Typography sx={{textAlign: 'center'}} variant='h5'>Авторизация</Typography>

      {checkAuthError && (
        <Alert severity="error">
          <AlertTitle>Ошибка</AlertTitle>
          Неверный логин или пароль
        </Alert>
      )}
      <TextField id="email" label="Email" variant="outlined" onChange={(event) => setEmail(event.target.value)}/>
      <TextField id="password" label="Пароль" variant="outlined" type="password" onChange={(event) => setPassword(event.target.value)}/>
      <Button variant='contained' sx={{mt: '15px'}} onClick={onSubmit}>Войти</Button>
    </Container>
  )
}
