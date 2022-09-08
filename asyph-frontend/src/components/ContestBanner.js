import {
  Button,
  Card,
  CardActions,
  CardContent,
  CardMedia,
  Typography,
} from "@mui/material";
import React from "react";
const ContestBanner = () => {
  return (
    <div style={{maxWidth: "100%"}}>
      <Card
        className="recent-contest--card"
      >
        <CardMedia
            component="img"
            height="100"
            image={process.env.PUBLIC_URL + '/banner.jpg'}
        />
        <CardContent className="recent-contest--card-content"
            style={{backgroundColor:"#34485f"}}
        >
          <Typography variant="h5" style={{color: "whitesmoke"}}>
            Biweekly Contest 75
          </Typography>
        </CardContent>
        <CardActions className="recent-contest--card-content"
            style={{backgroundColor: "#34485f"}}
        >
          <Button style={{color: "white", backgroundColor: "#02acff", borderRadius: "100px", minWidth: "150px"}}>Register</Button>
        </CardActions>
      </Card>
    </div>
  );
};

export default ContestBanner;
