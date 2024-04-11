import { Box, Button, Container, TextField, Typography } from '@mui/material'
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';

export const PCompetitionsCreate = () => {
  const { register, handleSubmit } = useForm();
  const navigate = useNavigate()

  const onSubmit = async (data: Record<string, string>) => {
    const response = await fetch('/api/contests', {
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        authorId: localStorage.getItem('userId'),
        name: data.name
      })
    })
    const result = await response.json()
    await fetch('/api/questions', {
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        contestId: result.id,
        text: data.text,
        correctAnswer: data.correctAnswer,
      })
    })
    navigate(-1)
  };
  
  return (
    <>
       <Container
        component='main'
        sx={{
          height: 'calc(100% - 400px)',
          py: '20px'
        }}
      >
        <Box component='form' sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'stretch',
          gap: '20px'
        }} onSubmit={handleSubmit(onSubmit)}>
          <Typography variant='h5'>Создание соревнования</Typography>
          <TextField required id="name" label="Название" variant="outlined" {...register('name')}/>
          <TextField required multiline minRows={20} maxRows={30} label='Текст и задание' {...register('text')}/>
          <TextField required id="correctAnswer" label="Ответ" variant="outlined" {...register('correctAnswer')}/>
          <Button variant='contained' sx={{mt: '15px'}} type='submit'>Создать</Button>
        </Box>
      </Container>
    </>
  )
}
