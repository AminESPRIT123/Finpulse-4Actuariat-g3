
from pydantic import BaseModel, Field
from typing import Optional, Literal
from datetime import datetime


class NotificationCreate(BaseModel):
    companyId: str
    alertId: Optional[str] = None
    title: str = Field(..., min_length=1)
    message: str = Field(..., min_length=1)
    severity: Literal["LOW", "MEDIUM", "HIGH"] = "MEDIUM"


class NotificationUpdate(BaseModel):
    title: Optional[str] = None
    message: Optional[str] = None
    severity: Optional[Literal["LOW", "MEDIUM", "HIGH"]] = None
    read: Optional[bool] = None


class NotificationResponse(BaseModel):
    id: str
    companyId: str
    alertId: Optional[str] = None
    title: str
    message: str
    severity: Literal["LOW", "MEDIUM", "HIGH"]
    read: bool
    createdAt: datetime
    readAt: Optional[datetime] = None
