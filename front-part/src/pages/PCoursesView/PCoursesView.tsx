import { Button, Container, Typography } from '@mui/material'
import { useContext, useEffect, useMemo, useState } from 'react'
import {useNavigate, useParams} from 'react-router-dom'
import { RatingContext } from '../../context/rating'

type Course = {
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

export const PCoursesView = () => {
  const params = useParams()

  const [course, setCourse] = useState<Course>()

  const navigate = useNavigate()
  const data = useContext(RatingContext);
  const isSuccess = useMemo(() => data.infoRating.coursesEnded.find(element => parseInt(element) === parseInt(params.id)), [])

  useEffect(() => {
    const fn = async () => {
      const accountsServer = await (await fetch('/api/accounts')).json()
      const courseServer = await (await fetch(`/api/courses/${params.id}`)).json()
      const questionsServer = await (await fetch(`/api/questions`)).json()
      const author = accountsServer.find((item: { id: number; }) => item.id === courseServer.authorId)
      const question = questionsServer.find((item: { contestId: number; }) => item.contestId === courseServer.id)

      setCourse({
        ...courseServer,
        question: question,
        author: `${author.firstName} ${author.lastName}`
      })
    }
    fn()
  }, [params.id])


  const onSubmit = () => {
    console.log('test')
    data.handleAddCourse()
    data.handleAddEndedCourse(params.id)
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
        <Typography variant='h5'>{course?.name}</Typography>
        <Typography variant='caption'>Автор: {course?.author}</Typography>
        <Typography border={'1px solid black'} sx={{
          height: '500px',
          borderRadius: '10px',
          padding: '10px'
        }}>
          {course?.question?.text}
        </Typography>
        <Button variant='contained' sx={{mt: '15px'}} disabled={isSuccess} onClick={onSubmit}>Завершить</Button>
      </Container>
    </>
  )
}
