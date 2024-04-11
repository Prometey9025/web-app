import { Box } from "@mui/system"
import { BCourseCard, BRanking } from "../../blocks"
import { Button, Grid, Typography } from "@mui/material"
import useMediaQuery from '@mui/material/useMediaQuery';
import { useTheme } from '@mui/material/styles';
import { Link } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { RatingContext } from "../../context/rating";

type Course = {
  id: number,
  name: string,
  author: string
}[] 

export const PCoursesList = () => {
  const theme = useTheme();
  const matchesXL = useMediaQuery(theme.breakpoints.up('xl'));
  const matchesMD = useMediaQuery(theme.breakpoints.up('md'));
  const [contests, setContests] = useState<Course>([])

  const data = useContext(RatingContext);

  useEffect(() => {
    const fn = async () => {
      const accountsServer = await (await fetch('/api/accounts')).json()
      const contestsServer = await (await fetch('/api/courses')).json()
      const result = contestsServer.map((contest: { authorId: number; }) => {
        const author = accountsServer.find((item: { id: number; }) => item.id === contest.authorId)

        return {
          ...contest,
          author: `${author.firstName} ${author.lastName}`
        }
      })

      setContests(result)
    }
    fn()
  }, [])

  return (
    <Grid container spacing={3}>
      <Grid item xs={10}>
        <Box sx={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
        }}>
          <Box component='h2' sx={{ml: '15px'}}>Курсы</Box>
          <Button 
            component={Link}
            to={'create'}
            variant='contained' 
            sx={{
              height: 'fit-content'
            }}>
              Создать курс
          </Button>
        </Box>
        {
          contests.length ? (
            <Grid container spacing={3} columns={matchesXL ? 3 : matchesMD ? 2 : 1}>
              {contests.map(item => {
                const isSuccess = data.infoRating.coursesEnded.find(element => parseInt(element) === parseInt(item.id))
                return <BCourseCard key={`card_course_${item.id}`} name={item.name} author={item.author} id={item.id} success={isSuccess}/>
              })}
            </Grid>
          ) : (
            <Typography sx={{width: '100%', textAlign: 'center'}} variant='h5'>Пусто</Typography>
          )
        }
      </Grid>
      <Grid item xs={2}>
        <BRanking view='courses'/>
      </Grid>
    </Grid>
  )
}
