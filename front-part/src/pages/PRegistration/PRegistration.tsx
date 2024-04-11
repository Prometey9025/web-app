import { useForm } from "react-hook-form";
import { Box, Button, Container, TextField, Typography } from "@mui/material"
import { useNavigate } from "react-router-dom";

export const PRegistration = () => {
  const { register, handleSubmit } = useForm();
  const navigate = useNavigate()

  const onSubmit = async (data: Record<string, string>) => {
    const response = await fetch('/api/accounts', {
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data)
    })
    const result = await response.json()

    console.log(result)
    localStorage.setItem("lastName", data.lastName);
    localStorage.setItem("firstName", data.firstName);
    localStorage.setItem("email", data.email);
    localStorage.setItem("userId", result.id);
    localStorage.setItem("password", result.password);
    localStorage.setItem("auth", 'true');
    navigate('/сompetitions')
  };
  
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
      <Typography sx={{textAlign: 'center'}} variant='h5'>Регистрация</Typography>
      <Box component='form' sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'stretch',
        gap: '20px'
      }} onSubmit={handleSubmit(onSubmit)}>
        <TextField required id="name" label="Имя" variant="outlined" {...register("firstName", {required: true})}/>
        <TextField required id="lastName" label="Фамилия" variant="outlined" {...register("lastName", {required: true})}/>
        <TextField required id="email" label="Email" variant="outlined" {...register("email", {required: true})}/>
        <TextField required id="password" label="Пароль" variant="outlined" type="password" {...register("password", {required: true})}/>
        <TextField required id="repeat-password" label="Подтвердите пароль" variant="outlined" type="password"/>
        <Button variant='contained' sx={{mt: '15px'}} type='submit'>Зарегистрироваться</Button>
      </Box>
    </Container>
  )
}