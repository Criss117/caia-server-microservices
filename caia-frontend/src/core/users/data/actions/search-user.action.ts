"use server";

import { AxiosError } from "axios";
import { CommonResponse } from "@/core/shared/models/type";
import { UserEntity } from "../entities/user.entity";
import { httpClient } from "@/core/shared/lib/config/http.config";

async function searchUsersAction(
  query: string,
  jwt: string
): Promise<CommonResponse<UserEntity[]>> {
  try {
    const { data } = await httpClient.get<CommonResponse<UserEntity[]>>(
      `/users/find/${query}`,
      {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      }
    );

    return {
      data: data.data,
      status: data.status,
      message: data.message,
      error: data.error,
    };
  } catch (error) {
    if (error instanceof AxiosError) {
      return {
        data: [],
        status: error.response?.status || 500,
        message: error.response?.data.message,
        error: error.response?.data.error,
      };
    }

    return {
      data: [],
      status: 500,
      message: "Error al registrar el usuario",
      error: "Error al registrar el usuario",
    };
  }
}

export default searchUsersAction;
