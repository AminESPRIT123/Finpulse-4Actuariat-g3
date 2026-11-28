
from datetime import datetime, timezone
from bson import ObjectId

from app.config.database import notifications_collection


class NotificationRepository:

    async def create(self, notification: dict):
        notification["read"] = False
        notification["createdAt"] = datetime.now(timezone.utc)
        notification["readAt"] = None

        result = await notifications_collection.insert_one(notification)

        return await self.get_by_id(str(result.inserted_id))

    async def get_all(self, company_id: str = None):
        query = {}

        if company_id:
            query["companyId"] = company_id

        cursor = notifications_collection.find(query)
        return await cursor.to_list(length=100)

    async def get_by_id(self, notification_id: str):
        if not ObjectId.is_valid(notification_id):
            return None

        return await notifications_collection.find_one(
            {"_id": ObjectId(notification_id)}
        )

    async def update(self, notification_id: str, data: dict):
        if not ObjectId.is_valid(notification_id):
            return None

        if data.get("read") is True:
            data["readAt"] = datetime.now(timezone.utc)

        elif data.get("read") is False:
            data["readAt"] = None

        result = await notifications_collection.update_one(
            {"_id": ObjectId(notification_id)},
            {"$set": data}
        )

        if result.matched_count == 0:
            return None

        return await self.get_by_id(notification_id)

    async def delete(self, notification_id: str):
        if not ObjectId.is_valid(notification_id):
            return False

        result = await notifications_collection.delete_one(
            {"_id": ObjectId(notification_id)}
        )

        return result.deleted_count > 0
