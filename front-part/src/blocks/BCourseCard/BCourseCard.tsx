import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { CardActionArea, Grid } from '@mui/material';
import { BCourseCardFC } from './types';
import DoneIcon from '@mui/icons-material/Done';
import { useNavigate } from 'react-router-dom';

export const BCourseCard: BCourseCardFC = ({name, author, id, success}) => {
  const navigate = useNavigate()

  return (
    <Grid item xs={1}>
      <Card sx={{ width: '100%' }} onClick={() => navigate(`${id}`)}>
        <CardActionArea>
          <CardContent>
            <Typography 
              gutterBottom 
              variant="h5" 
              component="h5" 
              sx={{
                display: 'flex', 
                alignItems: 'center', 
                justifyContent: 'space-between'
              }}
            >
              {name} {success && <DoneIcon color='success'/>}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Автор: {author}
            </Typography>
          </CardContent>
        </CardActionArea>
      </Card>
    </Grid>
  )
}
