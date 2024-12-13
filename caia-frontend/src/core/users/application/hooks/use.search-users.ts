import { useSession } from "next-auth/react";
import { useState } from "react";
import searchUsersAction from "../../data/actions/search-user.action";
import { UserEntity } from "../../data/entities/user.entity";

const useSearchUsers = () => {
  const [users, setUsers] = useState<UserEntity[]>([]);
  const { data: session } = useSession();
  const [query, setQuery] = useState("");

  const onChangeQuery = async (query: string) => {
    setQuery(query);

    if (query.length < 2) return;

    const res = await searchUsersAction(query, session?.jwt || "");

    setUsers(res.data || []);
  };

  return {
    query,
    users,
    onChangeQuery,
  };
};

export default useSearchUsers;
