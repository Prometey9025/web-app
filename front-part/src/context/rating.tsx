import React, { useState } from "react";

export const RatingContext = React.createContext({});

export const RatingProvider = ({children}: {children: React.ReactNode}) => {
  const [coursesRating, setCoursesRating] = useState(0);
  const [competitionsRating, setCompetitionsRating] = useState(0);
  const [competitionsEnded, setCompetitionsEnded] = useState<Array<number>>([]);
  const [coursesEnded, setCoursesEnded] = useState<Array<number>>([]);

  const handleAddCourse = () => setCoursesRating(prev => prev + 1)
  const handleAddCompetitions = () => setCompetitionsRating(prev => prev + 1)
  const handleAddEndedCompetitions = (id: number) => setCompetitionsEnded(prev => [...prev, id])
  const handleAddEndedCourse = (id: number) => setCoursesEnded(prev => [...prev, id])

  return (
    <RatingContext.Provider
      value={{infoRating: {
        coursesRating, 
        competitionsRating, 
        competitionsEnded, 
        coursesEnded
      }, 
      handleAddCourse, 
      handleAddCompetitions, 
      handleAddEndedCompetitions, 
      handleAddEndedCourse}}
    >
      {children}
    </RatingContext.Provider>
  )
}