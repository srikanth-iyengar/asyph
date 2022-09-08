import { Typography, Card, CardContent, Avatar } from "@mui/material";
import React from "react";

const BestPerformers = () => {
  return (
    <div className="top--rated">
      <Card
        className="top--rated"
        style={{ backgroundColor: "#1f2846", paddingBottom: "10px" }}
      >
        <CardContent>
          <Typography
            variant="h4"
            style={{ backgroundColor: "#1f2846", color: "white" }}
          >
            Top Rated
          </Typography>
          <div
            className="row--div"
            style={{ paddingTop: "0.00001%", paddingBottom: "3%" }}
          >
            <Avatar className="col--div">S</Avatar>
            <Typography
              variant="h5"
              style={{ color: "white", marginLeft: "3%" }}
              className="col--div"
            >
              Srikanth Iyengar
            </Typography>
          </div>
          <div
            className="row--div"
            style={{ paddingTop: "0.00001%", paddingBottom: "3%" }}
          >
            <Avatar className="col--div">S</Avatar>
            <Typography
              variant="h5"
              style={{ color: "white", marginLeft: "10px" }}
              className="col--div"
            >
              Srikanth Iyengar
            </Typography>
          </div>

          <div
            className="row--div"
            style={{ paddingTop: "0.00001%", paddingBottom: "3%" }}
          >
            <Avatar className="col--div">S</Avatar>
            <Typography
              variant="h5"
              style={{ color: "white", marginLeft: "10px" }}
              className="col--div"
            >
              Srikanth Iyengar
            </Typography>
          </div>
          <div
            className="row--div"
            style={{ paddingTop: "0.00001%", paddingBottom: "3%" }}
          >
            <Avatar className="col--div">S</Avatar>
            <Typography
              variant="h5"
              style={{ color: "white", marginLeft: "10px" }}
              className="col--div"
            >
              Srikanth Iyengar
            </Typography>
          </div>
        </CardContent>
      </Card>
    </div>
  );
};

export default BestPerformers;
