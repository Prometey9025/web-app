import { Button, Container, TextField, Typography } from '@mui/material'
import { useContext, useEffect, useMemo, useState } from 'react'
import {useNavigate, useParams} from 'react-router-dom'
import { RatingContext } from '../../context/rating'


type Contest = {
  id: number,
  name: string,
  author: string,
  question: {
    text: string,
    contestId: number,
    correctAnswer: string,
    id: number,
  }
}

export const PCompetitionsView = () => {
  const params = useParams()
  const navigate = useNavigate()

  const [contest, setContest] = useState<Contest>()
  const data = useContext(RatingContext);

  const isSuccess = useMemo(() => data.infoRating.competitionsEnded.find(element => parseInt(element) === parseInt(params.id)), [])

  useEffect(() => {
    const fn = async () => {
      const accountsServer = await (await fetch('/api/accounts')).json()
      const contestsServer = await (await fetch(`/api/contests/${params.id}`)).json()
      const questionsServer = await (await fetch(`/api/questions`)).json()
      const author = accountsServer.find((item: { id: number; }) => item.id === contestsServer.authorId)
      const question = questionsServer.find((item: { contestId: number; }) => item.contestId === contestsServer.id)

      setContest({
        ...contestsServer,
        question: question,
        author: `${author.firstName} ${author.lastName}`
      })
    }
    fn()
  }, [params.id])
  
  const onSubmit = () => {
    console.log('test')
    data.handleAddCompetitions()
    data.handleAddEndedCompetitions(params.id)
    navigate(-1)
  }

  return (
    <>
      <Container
        component='main'
        sx={{
          display: 'flex',
          flexDirection: 'column',
          height: 'calc(100% - 400px)',
          gap: '20px',
          py: '20px'
        }}
      >
        <Typography variant='h5'>{contest?.name}</Typography>
        <Typography variant='caption'>Автор: {contest?.author}</Typography>
        <Typography border={'1px solid black'} sx={{
          height: '500px',
          borderRadius: '10px',
          padding: '10px',
          whiteSpace: 'pre-wrap'
        }}>
          {contest?.question?.text}
        </Typography>
        <TextField id="answer" label="Ответ" variant="outlined" disabled={isSuccess}/>
        <Button variant='contained' sx={{mt: '15px'}} onClick={onSubmit} disabled={isSuccess}>Завершить</Button>
      </Container>
    </>
  )
}
