import Grid from '@mui/material/Grid/Grid';
import {BLayoutHeaderFC} from './types'

export const BLayoutHeader: BLayoutHeaderFC = ({leftContent, centerContent, rightContent}) => {
  return (
    <Grid
      container
      columns={3}
    >
      <Grid
        item
        xs={1}
        display='flex'
        justifyContent='flex-start'
        alignItems='center'
        gap='15px'
      >
        {leftContent}
      </Grid>
      <Grid
        item
        xs={1}
        display='flex'
        justifyContent='center'
        alignItems='center'
      >
        {centerContent}
      </Grid>
      <Grid
        item
        xs={1}
        display='flex'
        justifyContent='flex-end'
        alignItems='center'
        gap='15px'
      >
        {rightContent}
      </Grid>
    </Grid>
  );
};
